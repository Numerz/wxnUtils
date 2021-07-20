import csv

with open('val.csv', 'w', newline='') as csvfile:
    # 编码风格，默认为excel方式，也就是逗号(,)分隔
    spamwriter = csv.writer(csvfile, dialect='excel')
    # 读取txt文件，每行按逗号分割提取数据
    with open('val.txt', 'r') as file_txt:
        # for line in file_txt:
        #     line_datas = line.strip('\n').split(' ')
        #     spamwriter.writerow(line_datas)
        while 1:
            line = file_txt.readline()
            if not line:
                break
            line_data = line.strip('\n').split(' ')
            spamwriter.writerow(line_data)