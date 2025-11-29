        package Estudiantes.Estructuras;

        import Calificaciones.Estructuras.Nodo;
        import Estudiantes.Modelo.Estudiante;
        import Estudiantes.Modelo.NodoBST;

        public class BSTEstudiantes {
            private NodoBST raiz;
            public void insertarEstudiante(Estudiante estudiante) throws Exception {
                raiz = insertarRecursivo(raiz,estudiante);
            }
            public void eliminar(int matricula) throws Exception {
                raiz = eliminarRec(raiz, matricula);
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
            private NodoBST eliminarRec(NodoBST nodo, int matricula) throws Exception {
                if (nodo == null) {
                    throw new Exception("No se encontró el estudiante con matrícula " + matricula);
                }

                int actual = nodo.getEstudiante().getMatricula();

                if (matricula < actual) {
                    nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), matricula));
                }
                else if (matricula > actual) {
                    nodo.setDerecho(eliminarRec(nodo.getDerecho(), matricula));
                }
                else {

                    if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                        return null;
                    }


                    if (nodo.getIzquierdo() == null) {
                        return nodo.getDerecho();
                    }
                    if (nodo.getDerecho() == null) {
                        return nodo.getIzquierdo();
                    }


                    NodoBST sucesor = encontrarMin(nodo.getDerecho());
                    nodo.setEstudiante(sucesor.getEstudiante());
                    nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getEstudiante().getMatricula()));
                }

                return nodo;
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
            private NodoBST encontrarMin(NodoBST nodo) {
                while (nodo.getIzquierdo() != null) {
                    nodo = nodo.getIzquierdo();
                }
                return nodo;
            }


            public int contarNodos() {
                return contarNodosRec(raiz);
            }

            private int contarNodosRec(NodoBST nodo) {
                if (nodo == null) return 0;
                return 1 + contarNodosRec(nodo.getIzquierdo()) + contarNodosRec(nodo.getDerecho());
            }
            public Estudiante obtenerEnPosicion(int pos) {
                Contador c = new Contador();
                return obtenerEnPosicionRec(raiz, pos, c);
            }

            private Estudiante obtenerEnPosicionRec(NodoBST nodo, int pos, Contador c) {
                if (nodo == null) return null;

                Estudiante izq = obtenerEnPosicionRec(nodo.getIzquierdo(), pos, c);
                if (izq != null) return izq;

                if (c.valor == pos) return nodo.getEstudiante();
                c.valor++;

                return obtenerEnPosicionRec(nodo.getDerecho(), pos, c);
            }

            private static class Contador {
                int valor = 0;
            }




        }
