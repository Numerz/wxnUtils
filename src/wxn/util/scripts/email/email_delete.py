import keyring
from imbox import Imbox
import datetime

user_name = "numerz@qq.com"

# keyring.set_keyring("<service>", "<user>", "<pwd>")
pwd = keyring.get_password("qq", user_name)

# imap服务器地址，邮箱，密码，是否支持ssl
with Imbox('imap.qq.com', user_name, pwd, ssl=True) as imbox:
    # 未读、日期在2021.7.1之后
    all_inbox_messages = imbox.messages(unread=True, date__gt = datetime.date(2021,7,1))
    for uid, message in all_inbox_messages:
        subject = message.subject
        if "email-delete-test" in subject:
            print(message.subject)
            print(message.body['plain'])
            imbox.delete(uid)
            print('delete [uid=%d,subject=%s]' % (int(uid), subject))
