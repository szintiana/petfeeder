from tkinter import *
from tkinter import ttk
from tkinter import filedialog
from predict import *
import tkinter.messagebox as msgbox
from PIL import Image

root = Tk()
root.title("Cat, Dog, or Wild animal recognition APP")
root.geometry("1250x600+70+20")
root.configure(bg='#3b5998')

# Find prediction
def pred_photo():
    if os.path.isdir('images') is False:
        os.makedirs('images')
        os.makedirs('images/predict')
        os.makedirs('images/predict/cat')
        os.makedirs('images/predict/dog')
        os.makedirs('images/predict/wild')
    pred_path = 'images/predict'
    for index,x in enumerate(list_file.get(0,END)):
        image = Image.open(x)
        temp_path = 'images/predict/cat'
        predict_path = os.path.join(temp_path, '{}.jpg'.format(index))
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

    result_wind.config(state=NORMAL)
    result_wind.insert(END, total_resp)
    result_wind.config(foreground='#000000',font=("Helvetica", 14))
    result_wind.config(state=DISABLED)

# Add photo
def add_photo():
    photos = filedialog.askopenfilenames(title="Please select image files", \
            filetypes=(("JPG file", "*.jpg"), ("All types", "*.*")), \
            initialdir="C:/")
    for photo in photos:
        list_file.insert(END, photo)
# Delete photo
def del_photo():
    for index in reversed(list_file.curselection()):
        list_file.delete(index)
# Clear All
def clear_all():
    list_file.delete(0,END)
    result_wind.config(state=NORMAL)
    result_wind.delete("1.0",END)
    result_wind.config(state=DISABLED)

def start():
    if list_file.size() == 0:
        msgbox.showwarning("Warning", "Please add photos")
        return
    pred_photo()

# Photo frame
photo_frame = Frame(root,bg='#3b5998')
photo_frame.pack(fill='x', padx=5, pady=5)
btn_photo_add = Button(photo_frame, padx=5, pady=5, width=12, text="Choose photo", command=add_photo)
btn_photo_add.pack(side='left')
btn_photo_clear = Button(photo_frame, padx=5, pady=5, width=12, text="Clear All", command=clear_all)
btn_photo_clear.pack(side='right')
btn_photo_del = Button(photo_frame, padx=5, pady=5, text="Delete selected image", command=del_photo)
btn_photo_del.pack(side='right')

# List frame
list_frame = LabelFrame(root, text="Selected Images",bg='#3b5998',fg='#ffffff',font='Helvetica 18 bold')
list_frame.pack(fill='both', padx=5, pady=5)
scrollbar = Scrollbar(list_frame)
scrollbar.pack(side='right', fill='y')
list_file = Listbox(list_frame, selectmode='extended', height=15, yscrollcommand=scrollbar.set, bg='#dfe3ee')
list_file.pack(side='left', fill='both', expand=True)
scrollbar.config(command=list_file.yview)

# Result frame
label_book = Label(root,text="Predicted Results:", font='Helvetica 18 bold',bg='#3b5998',fg='#ffffff')
label_book.place(x=10,y=350)
result_wind = Text(root,bg='#dfe3ee',fg='#ffffff',font='Helvetica 14 bold')
scroll_window = Text(root, bd=0, bg='#dfe3ee')
scroll_window.place(x=1222,y=391,height=99,width=20)
scrollbar2 = Scrollbar(scroll_window)
scrollbar2.pack(side=LEFT, fill=Y)
result_wind.config(yscrollcommand=scrollbar2.set)
scrollbar2.config(command=result_wind.yview)
result_wind.config(state=DISABLED)
scroll_window.config(state=DISABLED)
result_wind.place(x=10, y=390, height=100, width=1212)

# Run frame
btn_close = Button(root, padx=5, pady=9, text="Close", width=12, command=root.quit)
btn_close.place(x=500,y=508)
btn_start = Button(root, padx=5, pady=9, text="Start", width=12, command=start)
btn_start.place(x=600,y=508)

root.resizable(False,False)
root.mainloop()