select extract(month from start_date) month, car_id, count(*) records
from car_rental_company_rental_history
where to_char(start_date, 'YYYY-MM-DD') between '2022-08-01' and '2022-10-31'
  AND car_id in
      (select car_id from car_rental_company_rental_history
       where to_char(start_date, 'YYYY-MM-DD') between '2022-08-01' and '2022-10-31'
       group by car_id having count(*) >=5)
group by extract(month from start_date), car_id
order by month, car_id desc