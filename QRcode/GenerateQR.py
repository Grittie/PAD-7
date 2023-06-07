import qrcode


# Generate QR from link and save it with a custom filename
def generate(file,qr_name,qr_path,link, counter):
    print("generating QR...")
    img = qrcode.make(link)
    string = qr_path + qr_name + str(counter) + file
    print(string)
    img.save(string)
