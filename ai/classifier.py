from imageai.Classification import ImageClassification
from os import path, getcwd


def setUpModel() -> ImageClassification:
    prediction = ImageClassification()
    prediction.setModelTypeAsDenseNet121()
    prediction.setModelPath(path.join(getcwd(), 'mobilnet_v2-model.pth'))
    prediction.loadModel()
    return prediction

# if __name__ == 'main':
#     prediction = setUpModel()
    # possibilities, probabilities = prediction.classifyImage('index.jpg')
    # for eachPrediction, eachProbability in zip(possibilities, probabilities):
    #     print(eachPrediction , " : " , eachProbability)