package com.oznur.finalcase.model;

import java.util.LinkedList;

public class Weather{

    private String cod;
    private float message;
    private float cnt;
    private LinkedList<Object> list = new LinkedList<>();
    private City city;

    public static class City {
        private float id;
        private String name;
        private Coord coord;
        private String country;
        private float population;
        private float timezone;
        private float sunrise;
        private float sunset;

        public static class Coord {
            private float lat;
            private float lon;

            public float getLat() {
                return lat;
            }

            public void setLat(float lat) {
                this.lat = lat;
            }

            public float getLon() {
                return lon;
            }

            public void setLon(float lon) {
                this.lon = lon;
            }
        }

        public float getId() {
            return id;
        }

        public void setId(float id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public float getPopulation() {
            return population;
        }

        public void setPopulation(float population) {
            this.population = population;
        }

        public float getTimezone() {
            return timezone;
        }

        public void setTimezone(float timezone) {
            this.timezone = timezone;
        }

        public float getSunrise() {
            return sunrise;
        }

        public void setSunrise(float sunrise) {
            this.sunrise = sunrise;
        }

        public float getSunset() {
            return sunset;
        }

        public void setSunset(float sunset) {
            this.sunset = sunset;
        }
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public float getCnt() {
        return cnt;
    }

    public void setCnt(float cnt) {
        this.cnt = cnt;
    }

    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
