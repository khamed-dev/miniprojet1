# miniprojet1

----------------------------------------------------------------------
1-go inside first project and run docker-compose.yml with the CMD
docker-compose up

2-
there is  Two End-Points :
-- /api/login

You can try to log in with the ADMIN
{
"email" :"devmedk@gmail.com",
"password":"med"
}

OR

with the USER
{
"email" :"medk@gmail.com",
"password":"med"
}

To get JWT token

and try to hit the endpoint sending along the Bearer JWT token in the header:

-- /api/admin/run

To upload the csv file to database using spring batch by adding Authorization to the header.
(works only for the ADMIN )
