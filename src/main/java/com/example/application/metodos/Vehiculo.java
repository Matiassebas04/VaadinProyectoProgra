    package com.example.application.metodos;

    import java.io.Serializable;
    import java.util.Objects;

    public class Vehiculo implements Serializable {
        private String tipoAuto;
        private String placaAuto;

        public Vehiculo(String tipoAuto, String placaAuto) {
            this.tipoAuto = tipoAuto;
            this.placaAuto = placaAuto;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Vehiculo vehiculo = (Vehiculo) obj;
            return Objects.equals(tipoAuto, vehiculo.tipoAuto) &&
                    Objects.equals(placaAuto, vehiculo.placaAuto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tipoAuto, placaAuto);
        }

        public String getTipoAuto() {
            return tipoAuto;
        }

        public void setTipoAuto(String tipoAuto) {
            this.tipoAuto = tipoAuto;
        }

        public String getPlacaAuto() {
            return placaAuto;
        }

        public void setPlacaAuto(String placaAuto) {
            this.placaAuto = placaAuto;
        }

        private static final long serialVersionUID = 1L; // Agrega esto para la serialización
    }
