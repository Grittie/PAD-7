import os
import time

from GenerateQR import generate
from auth import authenticate

# Method to upload a picture to Imgur.
# It needs the client and the path where the pic is stored in the PC

def upload_picture(client, path):
    foto = client.upload_from_path(path, config=None, anon=False)  # No config(name, date etc, and no anonymous)
    print("Done")
    print()
    print("De link: {0}".format(foto['link']))  # works like printf in Java
    link = '{0}'.format(foto['link'])
    return link  # Return the link, so it can be stored in a variable.


count = 1  # Can be used if we want te store/count the pictures.

client = authenticate()

image_path = 'QRcodes/results/'  # Where the results pics from Java where saved.
image_name = 'result'  # The name from Java
qr_code_path = 'QRcodes/QRs/'  # Where the QR code will be saved
image_file = '.png'

while True:

    qr_code_name = 'testcode_' + str(count)

    result_path = image_path + image_name + image_file
    qr_path = qr_code_path + qr_code_name + image_file
    try:
        results = upload_picture(client, result_path)
        generate(image_file, qr_code_name, qr_code_path, results)
        time.sleep(2)
        print("removing result file")
        os.remove(result_path)
        time.sleep(40)
        print("removing QR code")
        os.remove(qr_path)
        # count += 1
    except FileNotFoundError:
        print("waiting on file....")
        time.sleep(5)

# if we want te store/count the pictures the os.remove must be deleted and line 41 need to be un-comment
