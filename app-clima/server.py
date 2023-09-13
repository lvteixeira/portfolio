from flask import Flask, render_template, request, redirect
from util import search

'''flask app'''

app = Flask(__name__)

search_data = {'search result': {'nome': '', 'descricao': '', 'temp_atual': ''}}


@app.route('/')
def index():
    return render_template('index.html', search_data=search_data)


@app.route('/weather/geo/<float(signed=True):user_lat>/<float(signed=True):user_lon>')
def geo_weather(user_lat, user_lon):
    return search.weather_by_geolocation(user_lat, user_lon)


@app.route('/weather/name/', methods=["POST"])
def city_weather():
    user_city_name = request.form['city_name']
    search_data['search_result'] = search.weather_by_city_name(user_city_name)
    return redirect('/')


@app.route('/weather/name/', methods=["GET"])
def city_weather_redir():
    return redirect('/')


if __name__ == '__main__':
    app.run(debug=True, port=8080, host='localhost')