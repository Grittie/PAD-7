import configparser
import os
import time
from datetime import datetime

from imgurpython import ImgurClient

from GenerateQR import generate
from auth import authenticate


def getLink(client):
    items = client.gallery()
    max_item = None
    max_views = 0
    for item in items:
        if item.views > max_views:
            max_item = item
            max_views = item.views
    print(max_item.link)
    generate(max_item.link, 1)


def getPicture():
    # for album in client.get_account_albums('me'):
    #     album_title = album.title if album.title else 'Untitled'
    # print('Album: {0} ({1})'.format(album_title, album.id))

    for image in client.get_album_images('zG3f1T6'):
        # image_title = image.title if image.title else 'Untitled'
        print('\t{0}: {1}'.format(image.title, image.link))
    return image.link


def uploadPicture(client, path):
    print("Uploading image... ")
    foto = client.upload_from_path(path, config=None, anon=False)
    print("Done")
    print()
    print("De link: {0}".format(foto['link']))
    link = '{0}'.format(foto['link'])
    print(link)
    return link


QR_path = "de png van qr code"

restults_album_id = 1124
qr_album_id = 124

teller = 1

client = authenticate()


while True:
    image_path = 'QRcodes/results/'
    image_file = '.png'
    image_name = 'result'

    result_path = image_path + image_name + image_file
    try:
        results = uploadPicture(client, result_path)
        resultsQR = generate(results, teller)
        time.sleep(2)
        print("removing result file");
        os.remove(result_path)
    except FileNotFoundError:
        print("waiting on file....")
        time.sleep(5)



