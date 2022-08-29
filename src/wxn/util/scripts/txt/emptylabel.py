import os
import json
import base64
from PIL import Image


dir_path = r"C:\Users\chen\Desktop\clean"

example_path = r'C:\Users\chen\Desktop\example.json'

transfer_path = r"C:\Users\chen\Desktop\clean-trans"

# prefix_dir = "test"

# json_files = []
with open(example_path,'r',encoding='utf8') as f:
    # print(f)
    json_file = json.load(f)
    # print(json_file)

    files = os.listdir(dir_path)


    for file in list(files):
        json_file['imagePath'] = file

        with open (dir_path+'\\'+file,'rb') as p:
            print(p)
            img = p.read()
            # print(img)
            img_base64 = str(base64.b64encode(img), encoding='utf-8')
            json_file['imageData'] = img_base64

            img_PIL= Image.open(dir_path+'\\'+file)
            json_file['imageHeight'] = img_PIL.height
            json_file['imageWidth'] = img_PIL.width

            print(file)
            json_filename = str(file).split('.')[0]+'.json'
            print(json_filename)

            with open(transfer_path + "\\" + json_filename, 'w',encoding='utf-8') as f:
                json_str = json.dumps(json_file, ensure_ascii=False, indent=1)
                f.write(json_str)

            # print(json_file)


