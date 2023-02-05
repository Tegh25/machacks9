from flask import Flask
import requests
import classifier

app = Flask(__name__)

# endpoint /statuses/id
POLLING_ENDPOINT = 'https:example.com/processing'
RESPONSE_ENDPOINT = 'https:example.com/status/' # append the id to this when responding

def poll():
    request = requests.get(POLLING_ENDPOINT, headers={ 'Accept': 'application/json'})
    return request.json()

def respond(animal: str, request_id: int):
    response = {
        'type': str(animal)
    }
    requests.post(RESPONSE_ENDPOINT + str(id), json = response) 

@app.route('/')
def begin_polling():
    model = classifier.setUpModel()
    
    while True:
        image_requests = poll()
        for request in image_requests:
            id = request['id']
            try:
                base64 = request['base64']
                animal = classifier.classifyBase64(model, base64)
                respond(animal, id)
            except:
                respond('', id)