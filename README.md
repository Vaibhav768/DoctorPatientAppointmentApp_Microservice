--Project Title
   DoctorPatientAppointmentApp_Microservice

--Project Description
  Number of Services - 7
  1)Eureka server - Service Registration and Discovery. All the other services except Admin server and Config Server
                    are the clients of eureka server, runs on default port localhost:8761

  2)Admin server - managing non-functional features, runs on localhost:9999
  
  3)Config server - centralized configurations properties management common properties, i have used gitlab to store yaml file of configuration
                    all the services are act as client of config server, runs on localhost:8888
  
  4)Cloud API Gateway - All the microservice DPM_DoctorService, DPM_PatientService, DPM_AppointmentService are register with cloud api gateway
                        single entry point for client requests. which runs on port localhost:7777
  
  5)Doctor Service - Due to the close association between doctors and clinics,
                     I developed a single service to handle both clinic and doctor-related operations as Doctor Service.
                     This will also reduces the number rest calls.
                     
  6)Patient Service - To perform all the operations related to patient add, update, fetch, fetch all etc.
  
  7)Appointment Service - To perform the appointment scheduling, updating appointment, get appointment by ID.
                          here i used client component feign client for intra communication between microservices
                          while booking appointment we need doctor and patient details for that we need to make rest call.

--Tools Used for Development
     Java 17, Spring Boot 3, Oracle database, Eureka Discovery Server,
     Eureka Client, Admin Server, Admin Client, Spring Cloud API Gateway,
     Swagger, Postman, Maven, GitHub, GitLab

--API Gateway Endpoints
  1) Doctor Service -
     Doctors Operatins
     POST http://localhost:7777/doctorClinic-api/create-doctor
     PUT  http://localhost:7777/doctorClinic-api/update-doctor/{did}
     GET  http://localhost:7777/doctorClinic-api/doctor/{did}
     GET  http://localhost:7777/doctorClinic-api/all-doctors

     Clinic Operations -
     POST http://localhost:7777/doctorClinic-api/create-clinic
     PUT  http://localhost:7777/doctorClinic-api/update-clinic
     GET  http://localhost:7777/doctorClinic-api/clinic/{cid}
     GET  http://localhost:7777/doctorClinic-api/all-clinics

  2) Patient Service -
     POST http://localhost:7777/patient-api/create-patient
     PUT  http://localhost:7777/patient-api/update-patient
     GET  http://localhost:7777/patient-api//patient/{pid}
     GET  http://localhost:7777/patient-api/all-patients

  3) Appointment Service -
     POST http://localhost:7777/appointment-api/create-appointment
     PUT  http://localhost:7777/appointment-api/update-appointment/{aid}
     GET  http://localhost:7777/appointment-api/appointment/{pid}
     GET  http://localhost:7777/appointment-api/all-appointments
