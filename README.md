# miniprojet1
------------------------------------------------------------------------

---
1-go inside the first project and run docker-compose.yml with the CMD
docker-compose up

2-
there is Two End-Points :
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
to get Jwt token;

and try to hit this endpoint to run sending along jwt:

-- /api/admin/run

To upload the csv file to database using spring batch by adding Authorization to the header.
(works only for the ADMIN )
