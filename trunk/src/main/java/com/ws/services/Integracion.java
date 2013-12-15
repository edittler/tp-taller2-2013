

/**
 * Integracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.ws.services;

    /*
     *  Integracion java interface
     */

    public interface Integracion {
          
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  setMockService(
         com.ws.services.SetMockService setMockService0

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param eliminarDatos1
                
         */

         
                     public com.ws.services.EliminarDatosResponse eliminarDatos(

                        com.ws.services.EliminarDatos eliminarDatos1)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param eliminarDatos1
            
          */
        public void starteliminarDatos(

            com.ws.services.EliminarDatos eliminarDatos1,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param actualizarArchivo3
                
         */

         
                     public com.ws.services.ActualizarArchivoResponse actualizarArchivo(

                        com.ws.services.ActualizarArchivo actualizarArchivo3)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param actualizarArchivo3
            
          */
        public void startactualizarArchivo(

            com.ws.services.ActualizarArchivo actualizarArchivo3,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param guardarArchivo5
                
         */

         
                     public com.ws.services.GuardarArchivoResponse guardarArchivo(

                        com.ws.services.GuardarArchivo guardarArchivo5)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param guardarArchivo5
            
          */
        public void startguardarArchivo(

            com.ws.services.GuardarArchivo guardarArchivo5,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param isMock7
                
         */

         
                     public com.ws.services.IsMockResponse isMock(

                        com.ws.services.IsMock isMock7)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param isMock7
            
          */
        public void startisMock(

            com.ws.services.IsMock isMock7,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param actualizarDatos9
                
         */

         
                     public com.ws.services.ActualizarDatosResponse actualizarDatos(

                        com.ws.services.ActualizarDatos actualizarDatos9)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param actualizarDatos9
            
          */
        public void startactualizarDatos(

            com.ws.services.ActualizarDatos actualizarDatos9,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param seleccionarDatos11
                
         */

         
                     public com.ws.services.SeleccionarDatosResponse seleccionarDatos(

                        com.ws.services.SeleccionarDatos seleccionarDatos11)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param seleccionarDatos11
            
          */
        public void startseleccionarDatos(

            com.ws.services.SeleccionarDatos seleccionarDatos11,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param guardarDatos13
                
         */

         
                     public com.ws.services.GuardarDatosResponse guardarDatos(

                        com.ws.services.GuardarDatos guardarDatos13)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param guardarDatos13
            
          */
        public void startguardarDatos(

            com.ws.services.GuardarDatos guardarDatos13,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param eliminarArchivo15
                
         */

         
                     public com.ws.services.EliminarArchivoResponse eliminarArchivo(

                        com.ws.services.EliminarArchivo eliminarArchivo15)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param eliminarArchivo15
            
          */
        public void starteliminarArchivo(

            com.ws.services.EliminarArchivo eliminarArchivo15,

            final com.ws.services.IntegracionCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    