import requests
from unidecode import unidecode

API_KEY = 'cf6bdeee4d6b71d455d6722d7c292b64'
UNITS = 'metric'
LANG = 'pt_br'

def weather_by_city_name(city_name:str):
    city_name = unidecode(city_name.lower())

    '''clima por nome da cidade'''
    url = f'http://api.openweathermap.org/data/2.5/weather?q={city_name}&appid={API_KEY}&units={UNITS}&lang={LANG}'
    try:
        response = requests.get(url)
        if response.status_code == 200:
            d_response = response.json()
            return dict({'icon_id': d_response['weather'][0]['id'],
                         'nome': d_response['name'],
                         'descricao': d_response['weather'][0]['description'],
                         'temp_atual': f"{d_response['main']['temp']:.0f}",
                         'temp_min': f"{d_response['main']['temp_min']:.0f}",
                         'temp_max': f"{d_response['main']['temp_max']:.0f}",
                         'sens_term': f"{d_response['main']['feels_like']:.0f}"
                        })
        if response.status_code == 404:
            return dict({'status_code': response.status_code, 
                        'status': 'erro404', 
                        'response': response.json()
                        })
    except:
        raise Exception(ConnectionError)


"""
def weather_by_city_id(city_id):
    '''clima por id da cidade'''
    url = f'http://api.openweathermap.org/data/2.5/weather?id={city_id}&appid={API_KEY}&units={UNITS}&lang={LANG}'
    try:
        response = requests.get(url)
        if response.status_code == 200:
            d_response = response.json()
            return dict({'descricao': d_response['weather'][0]['description'],
                         'temp_atual': d_response['main']['temp'],
                         'temp_min': d_response['main']['temp_min'],
                         'temp_max': d_response['main']['temp_max'],
                         'sens_term': d_response['main']['feels_like']
                        })
        return dict({'status_code': response.status_code, 
                     'status': 'erro', 
                     'response': response.json()
                    })
    except:
        raise Exception(ConnectionError)
"""

def weather_by_geolocation(lat:float, lon:float):
    '''clima por geolocalizacao'''
    url = f'http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API_KEY}&units={UNITS}&lang={LANG}'
    try:
        response = requests.get(url)
        if response.status_code == 200:
            d_response = response.json()
            return dict({'nome': d_response['name'],
                         'descricao': d_response['weather'][0]['description'],
                         'temp_atual': d_response['main']['temp'],
                         'temp_min': d_response['main']['temp_min'],
                         'temp_max': d_response['main']['temp_max'],
                         'sens_term': d_response['main']['feels_like']
                        })
        return dict({'status_code': response.status_code, 
                     'status': 'erro', 
                     'response': response.json()
                    })        
    except:
        raise Exception(ConnectionError)