from tkinter import *
from tkinter import ttk
from tkinter import filedialog
from predict import *
import tkinter.messagebox as msgbox
from PIL import Image

def pred_photo():
    if os.path.isdir('images') is False:
        os.makedirs('images')
        os.makedirs('images/predict')
        os.makedirs('images/predict/cat')
        os.makedirs('images/predict/dog')
        os.makedirs('images/predict/wild')
    pred_path = 'images/predict'
    image = Image.open('get path here')
    temp_path = 'images/predict/cat'
    predict_path = os.path.join(temp_path, 'pred.jpg')
    image.save(predict_path)
    pred_batches= ImageDataGenerator(preprocessing_function=tf.keras.applications.vgg16.preprocess_input) \
                .flow_from_directory(directory=pred_path, target_size=(224,224), classes=['cat','dog','wild'], batch_size=10, shuffle=False)
    pred_result = predict_animal(pred_batches)
    for file in os.listdir(temp_path):
        if file.endswith('.jpg'):
            os.remove(os.path.join(temp_path,file))
    total_resp = ''
    for p_ind,pr in enumerate(pred_result):
        if pr == 0:
            response = 'The animal in image {} is predicted to be "Cat"'.format(p_ind+1)
        elif pr == 1:
            response = 'The animal in image {} is predicted to be "Dog"'.format(p_ind+1)
        else:
            response = 'The animal in image {} is predicted to be "Wild Animal"'.format(p_ind+1)
        total_resp += response + '\n'

pred_photo()