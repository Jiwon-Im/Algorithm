SELECT history_id, car_id,
       date_format(start_date, '%Y-%m-%d') start_date,
       date_format(end_date, '%Y-%m-%d') end_date,
       case
           when datediff(end_date, start_date) < 29
               then '단기 대여'
           else '장기 대여'
           end rent_type
from car_rental_company_rental_history
where date_format(start_date, '%Y-%m') = '2022-09'
order by history_id desc

-- datediff(a, b) 함수
-- * a-b (일 수)
-- * +1 해주기 (같은날 - 같은날 = 0 day)