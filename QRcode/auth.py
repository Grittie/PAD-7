import configparser
from imgurpython import ImgurClient


def authenticate():
    # Get client ID and secret from auth.ini
    config = configparser.ConfigParser()
    config.read('auth.ini')

    client_id = config.get('credentials', 'client_id')
    client_secret = config.get('credentials', 'client_secret')

    client = ImgurClient(client_id, client_secret)

    # Authorization flow, pin example (there are other ways but didn't work for me bcause i didn't get the verification code)
    authorization_url = client.get_auth_url('pin')
    print(authorization_url)
    pin = input('Wat is de pin:')
    credentials = client.authorize(pin, 'pin')
    client.set_user_auth(credentials['access_token'], credentials['refresh_token'])
    return client
