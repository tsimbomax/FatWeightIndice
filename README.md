# FatWeightIndice
Android Application for computation of fat weight indice (FWI).
This project is divised in two main paths, independs to each other.
*The first path is mainly about android with three activities, 
    - the first is devoted to the home, which lead to the other activities, 
    - the next one is devoted to the computation and interpretation of FWI for a given profile or modification of last/old profile,
    - the last one is devoted to the history.
 In this parth the persistence is ensure by either serialisation or local database using MySQLite
*The second path is about external persistence of data (cloud)
  At this level, we have delevop a Java Web application which interact with the android applcation throught HTTP protocol and caused external data persistence.
  This path is located into the folders :  'WebAppServer' and 'server'.
