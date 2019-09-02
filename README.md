
#Rental Manager

## Description of application

A vehicle rental company needs an application to manage vehicle rentals. The company rents
vehicles in 5 categories:
1. small car
2. family car
3. luxury car
4. small van
5. large van 

From the rental company&#39;s point of view each vehicle category represents a different type of
rental account and a record for a vehicle is an instance of a rental account.
All vehicles have a make and a registration number. Luxury cars have air conditioning. Other
vehicles may have air conditioning. For rental purposes, vehicles also have driver details, a
start date for a rental period and a booked end date for a rental period.
Drivers have a name, date of birth and driving licence. Driving licences can be full or
provisional. A full licence permits a driver to drive a car and may permit them to drive a
heavy goods vehicle. A provisional licence permits a driver to learn to drive a car. A licence
has a driver identification number.
The following rules govern vehicle rentals:
 to rent a small car, a driver must be 17 or over and have a full licence
 to rent a family car or a small van, a driver must be 21 or over and have a full licence
 to rent a luxury car, a driver must be 25 or over and have a full licence
 to rent large van,  a driver must be 25 or over and have a full licence that permits
them to drive heavy goods vehicles
The rental company uses the following charging scheme.
1. Rentals are based on full or partial 24 hour periods (that is, a rental day is any period
up to and including 24 hours).
2. The base rates for each category of vehicle are: £30 per day for a small car, £40 per
day for a family car, £150 per day for a luxury car, £50 per day for a small van and
£70 per day for a large van.
3. Air conditioning is charged at 10% extra per day except for luxury cars which do not
have a surcharge because air conditioning is included. For example, the rate for an
air-conditioned small car is £33 per day.
4. If a vehicle is returned early (before the booked end date), then there is a reduction in
charges. For small cars the reduction is 100% of the daily rate for each unused whole
day. For luxury cars the reduction is 30% of the daily rate for each unused whole day.
For the remaining vehicle categories, the reduction is 50% of the daily rate for each
unused whole day.
5. If a vehicle is returned late, there is a penalty surcharge on the daily rate for each full
or part day after the booked end date of the rental period. The surcharge is 30% per
day for luxury cars and 20% per day for the other vehicle categories.

The company needs an application to manage vehicle rentals that enforces the driver
eligibility rules and calculates charges to incurred. The application must only rent a vehicle
to a driver who is eligible to drive the vehicle (according to the company rules). At the end of
a rental period (from start date to return date), the application must calculate the charge
incurred by the driver according to the company&#39;s charging scheme.
You can make the following simplifying assumptions:
 the company has an unlimited supply of vehicles in all categories,
 there can only be one driver per vehicle, and
 the application does not retain any history of past rentals.
One consequence of these assumptions is that the application only concerns vehicles
currently out for rental (as opposed to all vehicles the rental company has in stock).