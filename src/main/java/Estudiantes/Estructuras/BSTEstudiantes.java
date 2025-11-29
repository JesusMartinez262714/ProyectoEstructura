        package Estudiantes.Estructuras;

        import Calificaciones.Estructuras.Nodo;
        import Estudiantes.Modelo.Estudiante;
        import Estudiantes.Modelo.NodoBST;

        public class BSTEstudiantes {
            private NodoBST raiz;
            public void insertarEstudiante(Estudiante estudiante) throws Exception {
                raiz = insertarRecursivo(raiz,estudiante);
            }
            public NodoBST buscarEstudiante(int matricula) throws Exception {
                return buscarRecursivo(raiz,matricula);
            }
            public void inOrden(java.util.function.Consumer<NodoBST> accion) {
                inOrdenRecursivo(raiz, accion);
            }

            public void preOrden() {
                preOrdenRecursivo(raiz);
            }
            public void postOrden() {
                postOrdenRecursivo(raiz);
            }

            private NodoBST insertarRecursivo(NodoBST nodoActual,Estudiante estudiante) throws Exception {
                if(nodoActual == null){
                    return new NodoBST(estudiante);
                }
                if(estudiante.getMatricula()<nodoActual.getEstudiante().getMatricula()){
                    nodoActual.setIzquierdo(insertarRecursivo(nodoActual.getIzquierdo(),estudiante));
                }else if(estudiante.getMatricula() > nodoActual.getEstudiante().getMatricula()){
                    nodoActual.setDerecho(insertarRecursivo(nodoActual.getDerecho(),estudiante));
                }else{
                    throw new Exception("Ya existe este estudiante");
                }
                return nodoActual;
            }

            private NodoBST buscarRecursivo(NodoBST nodoActual,int  matricula) throws Exception {
                if(nodoActual == null){
                    System.out.println("No existe estudiante");
                    return null;
                }
                if(nodoActual.getEstudiante().getMatricula() == matricula){
                    System.out.println("Estudiante encontrado");
                    return nodoActual;
                }else if(matricula < nodoActual.getEstudiante().getMatricula()){
                    return buscarRecursivo(nodoActual.getIzquierdo(),matricula);
                }

                return (buscarRecursivo(nodoActual.getDerecho(),matricula));
            }

            private void inOrdenRecursivo(NodoBST nodoActual, java.util.function.Consumer<NodoBST> accion) {
                if (nodoActual == null) {
                    return;
                }
                inOrdenRecursivo(nodoActual.getIzquierdo(), accion);
                accion.accept(nodoActual);
                inOrdenRecursivo(nodoActual.getDerecho(), accion);
            }

            private void preOrdenRecursivo(NodoBST nodoActual) {
                if (nodoActual == null) {
                    return;
                }
                System.out.println(nodoActual.getEstudiante());
                preOrdenRecursivo(nodoActual.getIzquierdo());
                preOrdenRecursivo(nodoActual.getDerecho());
            }
            private void postOrdenRecursivo(NodoBST nodoActual) {
                if (nodoActual == null) {
                    return;
                }

                postOrdenRecursivo(nodoActual.getIzquierdo());
                postOrdenRecursivo(nodoActual.getDerecho());
                System.out.println(nodoActual.getEstudiante());
            }





        }
