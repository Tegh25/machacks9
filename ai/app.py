from threading import Thread
from time import sleep
from flask import Flask
import classifier
import os
import requests

app = Flask(__name__)

# endpoint /statuses/id
POLLING_ENDPOINT = 'http://127.0.0.1:8080/processing'
RESPONSE_ENDPOINT = 'http://127.0.0.1:8080/status/' # append the id to this when responding

def poll():
    request = requests.get(POLLING_ENDPOINT)
    print(request.json())
    return request.json()

def respond(animal: str, request_id: int):
    response = {
        'type': str(animal)
    }
    requests.post(RESPONSE_ENDPOINT + str(request_id), json = response) 

def continuous_poll():
    model = classifier.setUpModel()
    while True:
        sleep(1)
        image_requests = poll()
        for request in image_requests:
            id = request['id']
            try:
                base64 = request['base64']
                animal = classifier.classifyBase64(model, base64)
                respond(animal, id)
            except:
                respond('', id)

@app.route('/')
def begin_polling():
    thread = Thread(target=continuous_poll)
    thread.start()
    return 'Polling Started'
    

if __name__ == '__main__':
    os.environ['NO_PROXY'] = '127.0.0.1'
    app.run(threaded=True, port=5000)