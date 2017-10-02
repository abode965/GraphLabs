/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author HandSome
 */
public class City {
        private String cityName, mayor; 

    public City() {
    }

        
        public City(String cityName, int pop,String mayor) {
            this.cityName = cityName;
            this.mayor = mayor;
            this.pop = pop;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getMayor() {
            return mayor;
        }

        public void setMayor(String mayor) {
            this.mayor = mayor;
        }

        public int getPop() {
            return pop;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }
      private int pop ;
}
