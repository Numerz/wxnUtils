# output file
with open("train.txt", 'w') as f_out:
    # input file
    with open("eigen_train_files.txt", 'r') as f_in:
         while 1:
            line = f_in.readline()
            if not line:
                break
            ###################### 字符串处理 #############################
            newline = "/home/wxn/KittiRaw/" + line
            # newline = "/home/wxn/imgReflect/" + line
            newline = newline.replace('image_03', 'image_02')
            newline = newline.replace('jpg', 'png')
            # newline = newline.replace(' ', ' /home/wxn/imgReflect/')
            newline = newline.replace(' ', ' /home/wxn/DRM_output/')
            # index_reflect = newline.find('imgReflect')
            # output = newline[:index_reflect + 10] + '/' + newline[index_reflect + 9 + 13 : index_reflect + 9 + 13 + 26] + '_' + newline[-15: ]
            # outputline = newline[:-4]
            # outputline += "bin\n"
            ###############################################################

            # f_out.write(outputline)
            f_out.write(newline)

