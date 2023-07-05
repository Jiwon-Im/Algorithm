SELECT car_id,
       max(
               case when '2022-10-16' between start_date and end_date
                        then '대여 가능'
                    else '대여중'
                   end) availability
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
order by car_id desc
