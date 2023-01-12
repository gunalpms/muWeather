import sys
import requests, json


# Enter your API key here
api_key = "95df0a2e333f7c4c596e2b2db78b9aed"

# base_url variable to store url
base_url = "http://api.openweathermap.org/data/2.5/weather?"

# Give city name
city_name = str(sys.argv[1])

# complete_url variable to store
# complete url address
complete_url = base_url + "appid=" + api_key + "&q=" + city_name + "&units=metric"

# get method of requests module
# return response object
response = requests.get(complete_url)

# json method of response object
# convert json format data into
# python format data
x = response.json()

# Now x contains list of nested dictionaries
# Check the value of "cod" key is equal to
# "404", means city is found otherwise,
# city is not found
if x["cod"] != "404":

    # store the value of "main"
    # key in variable y
    y = x["main"]
    w = x["wind"]
    s = x["sys"]
    country_code = s["country"]
    city_name = x["name"]

    # store the value corresponding
    # to the "temp" key of y
    current_temperature = y["temp"]
    current_wind_speed = w["speed"]
    current_wind_degree = w["deg"]
    # store the value corresponding
    # to the "pressure" key of y
    current_pressure = y["pressure"]

    # store the value corresponding
    # to the "humidity" key of y
    current_humidity = y["humidity"]

    # store the value of "weather"
    # key in variable z
    z = x["weather"]

    # store the value corresponding
    # to the "description" key at
    # the 0th index of z
    weather_description = z[0]["description"]

    # print following values
    print(current_temperature, current_pressure, current_humidity, 
    current_wind_speed, current_wind_degree, country_code, weather_description)

else:
    print("City Not Found")

if __name__ == '__main__':
    globals()[sys.argv[1]]()
    #should be 0 for successful exit
    #however just to demostrate that this value will reach Java in exit code
    sys.exit(220)