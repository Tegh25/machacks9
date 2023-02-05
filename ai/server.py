from flask import Flask
import requests

app = Flask(__name__)

# endpoint /statuses/id
POLLING_ENDPOINT = 'https:example.com/processing'
RESPONSE_ENDPOINT = 'https:example.com/status/' # append the id to this when responding

def poll():
    request = requests.get(POLLING_ENDPOINT, headers={ 'Accept': 'application/json'})
    return request.json()

@app.route('/')
def begin_polling():
    while True:
        image_requests = poll()