import os
import json
import base64


def readJsonFile(file_path, file_name, prefix, index):
    with open(file_path + "\\" + file_name, 'r', encoding='utf8') as f:
        json_data = json.load(f)
        transfer_path = file_path + "-转换"
        rename(transfer_path, json_data, prefix, index)


def rename(transfer_path, json_data, prefix, index):
    format_file = prefix + '-' + str(index)
    image_path = format_file + ".jpg"
    json_path = format_file + ".json"
    json_data['imagePath'] = image_path
    # ---- 保存到转换的文件夹中
    print(transfer_path)
    if not os.path.exists(transfer_path):
        os.makedirs(transfer_path)
        print("文件夹创建成功")
    img_data = base64.b64decode(json_data['imageData'])
    with open(transfer_path + "\\" + image_path, 'wb') as f:
        f.write(img_data)
    json_str = json.dumps(json_data, ensure_ascii=False)
    with open(transfer_path + "\\" + json_path, 'w') as f:
        f.write(json_str)


path = r".\picture_dir"
prefix_dir = "trainimg"
files = os.listdir(path)
json_files = []
for file in list(files):
    json_file_suffix = file.split(".")[-1]
    if json_file_suffix == 'json':
        json_files.append(file)

for i, json_file in enumerate(json_files):
    readJsonFile(path, json_file, prefix_dir, i)
