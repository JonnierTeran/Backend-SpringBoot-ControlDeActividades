# Descripcion del Proceso de Deploy en Google Cloud 
### paso a paso del proceso

- Enlace de guia : https://www.youtube.com/watch?v=D1GJKd62l-A&ab_channel=DATACLOUDER 
- Paso 1 : Registro en Google cloud: https://cloud.google.com/
- paso 2: entrar a la consola https://console.cloud.google.com/ 
- paso 3: descargar el sdk de google cloud: https://cloud.google.com/sdk/docs/install-sdk?hl=es-419 
- paso 4: ejecutar gcloud init para iniciar
- paso 5: Loguearse y elegir el proyecto en la terminal
- paso 6: verificar con el comando >gcloud config list que todo haya salido bien
- paso 7: Crear el gcloudignore para evitar subir los archivos de empaquetado y compilacion .mvn
target
mvnw
mvnw.cmd
- paso 8: cambiar el tipo en empaquetado en el pom.xml por jar en lugar de war
- paso 9: crear un destructor para indicar el tipo de aplicacion y la maquina que google usara
- paso 10: se crea un archivo llamado app.yaml runtime: java17
instance_class: F2 con esos datos
- paso 11: desplegar con gcloud app deploy y elegir la zona del servidor en este 16