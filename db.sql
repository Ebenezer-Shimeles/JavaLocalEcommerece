Use Ecomm;
create database Ecomm;
drop database Ecomm;
use master;
-----------------------user bloc----------------------------------------------------------------

drop table users;

create table users(id int primary key identity(1, 2), 
                   name varchar(30) not NULL, 
				   last_name varchar(30) not null, 
                   gender char(1) not NULL default 'M', 
				   email varchar(200) not null unique, 
				   password varchar(200) not null, 
                   age tinyint not null default 13, 
				   constraint AGE_CON check(age> 13),
				   balance money
);


/*
"Insert into users(name, email,last_name, gender, password, age, balance) "
    	    		+ "values('"+name+"','"+email+"' ,'"+lastName+"', '"+gender+"', '"+password+"', "+age+", 100 )
*/

---defining error messages here
EXEC sp_addmessage 
    @msgnum = 50001, 
    @severity = 1, 
    @msgtext = 'Invalid Email Address';

EXEC sp_addmessage 
    @msgnum = 50002, 
    @severity = 1, 
    @msgtext = 'Email is taken';

EXEC sp_addmessage 
    @msgnum = 50003, 
    @severity = 1, 
    @msgtext = 'Age must be at least 13 years old';


EXEC sp_addmessage 
    @msgnum = 50004, 
    @severity = 1, 
    @msgtext = 'Password is too short';

EXEC sp_addmessage 
    @msgnum = 50005, 
    @severity = 1, 
    @msgtext = 'Password is incorrect';



----
alter proc [login_user](@email varchar(30), @password varchar(30))
as
begin
  if (select count(*) from "users" where email=@email)  = 0
  begin
      raiserror('error no user found!', 15, 1)
	  return
  end
  declare @pass_in_db varchar(30)
  select top 1 @pass_in_db = "password" from [users] where email = @email
  if @pass_in_db != @password
  begin
     raiserror(50005, 15 ,10)
	 return
  end
  print concat(@pass_in_db, 'dsds');
   print('login successful');
end
select * from users;
exec [login_user] @email='Email', @password='Password';
exec [login_user] @email='e@b.com', @password='dsdsdsdsdssd'


alter proc [register_user](@name varchar(30), @lastName varchar(30), @gender varchar(1),
                            @email varchar(200), @password varchar(200), @age tinyint)
							as
begin
    if len(@password) < 6
	begin
	   raiserror(50004, 12, 1);
	   return;
	end
    if @age < 13
	begin
	   raiserror(50003, 12, -1);
	   return
	end
    if CHARINDEX('@', @email) = 0
	begin
	    print concat('@ is missing in', @email) ;
	    raiserror(50001, 12, 1);
		return
	end
	 if CHARINDEX('.', @email) = 0
	begin
	    print concat('. is missing in', @email) ;
	    raiserror(50001, 12, 1);
		return
	end
	if (select count(*) from users where email = @email) != 0
	begin
	    raiserror(50002, 12, 1);
		return
	end
	else
	begin
		Insert into users("name", email,last_name, gender, password, age, balance) 
    		values(@name, @email ,@lastName, @gender, @password, @age, 100);
	end

end
--test
select * from users;
exec [register_user] @name='dsds', @lastName='dsds', @gender='M', @email='e@b.com', @password='dsdsdsdefe383f0no3fnudlfndjfndfdfdd', @age=20;


exec [register_user] @name = 'dsds' ,@lastName= 'dsds' ,@gender='M' ,@email='dsds@dsds.com' ,@password='muzebelahu' ,@age=1



------------------------------------------------------------