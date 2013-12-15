
/**
 * IntegracionCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.ws.services;

    /**
     *  IntegracionCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class IntegracionCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public IntegracionCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public IntegracionCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for eliminarDatos method
            * override this method for handling normal response from eliminarDatos operation
            */
           public void receiveResulteliminarDatos(
                    com.ws.services.EliminarDatosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarDatos operation
           */
            public void receiveErroreliminarDatos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarArchivo method
            * override this method for handling normal response from actualizarArchivo operation
            */
           public void receiveResultactualizarArchivo(
                    com.ws.services.ActualizarArchivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarArchivo operation
           */
            public void receiveErroractualizarArchivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarArchivo method
            * override this method for handling normal response from guardarArchivo operation
            */
           public void receiveResultguardarArchivo(
                    com.ws.services.GuardarArchivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarArchivo operation
           */
            public void receiveErrorguardarArchivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isMock method
            * override this method for handling normal response from isMock operation
            */
           public void receiveResultisMock(
                    com.ws.services.IsMockResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isMock operation
           */
            public void receiveErrorisMock(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarDatos method
            * override this method for handling normal response from actualizarDatos operation
            */
           public void receiveResultactualizarDatos(
                    com.ws.services.ActualizarDatosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarDatos operation
           */
            public void receiveErroractualizarDatos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for seleccionarDatos method
            * override this method for handling normal response from seleccionarDatos operation
            */
           public void receiveResultseleccionarDatos(
                    com.ws.services.SeleccionarDatosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from seleccionarDatos operation
           */
            public void receiveErrorseleccionarDatos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarDatos method
            * override this method for handling normal response from guardarDatos operation
            */
           public void receiveResultguardarDatos(
                    com.ws.services.GuardarDatosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarDatos operation
           */
            public void receiveErrorguardarDatos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarArchivo method
            * override this method for handling normal response from eliminarArchivo operation
            */
           public void receiveResulteliminarArchivo(
                    com.ws.services.EliminarArchivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarArchivo operation
           */
            public void receiveErroreliminarArchivo(java.lang.Exception e) {
            }
                


    }
    