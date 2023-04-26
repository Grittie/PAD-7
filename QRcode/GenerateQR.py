import qrcode

## Generate QR from link and save it with a custom filename
def generate(link, teller):
    img = qrcode.make(link)
    path = "QRcodes/QRs/"
    name = "testcode_"
    file = ".png"
    string = path + name + str(teller) + file
    print(string)
    img.save(string)
    return str(string)


