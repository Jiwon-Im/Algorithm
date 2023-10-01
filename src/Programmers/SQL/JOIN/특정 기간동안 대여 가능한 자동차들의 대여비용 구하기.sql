select * from (
-- [조건] fee 30일간의 대여 금액이 50만원 이상 200만원 미만
                  SELECT c.car_id, c.car_type, round(c.daily_fee*30*(100-p.discount_rate)*0.01,0) fee
                  from  CAR_RENTAL_COMPANY_CAR c
                            join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type
                  where c.car_type in ('세단' ,'SUV')
-- [조건] 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능
                    and c.car_id not in
                        (select car_id
                         from CAR_RENTAL_COMPANY_RENTAL_HISTORY h
                         where (date_format(start_date,'%Y-%m')<'2022-11') and (date_format(end_date,'%Y-%m')>='2022-11')
                            or (date_format(start_date,'%Y-%m')='2022-11'))
-- [30일] discount_rate 구하기 위함
                    and p.duration_type = '30일 이상'
              ) a
where a.fee>=500000 and a.fee<2000000 -- [조건] fee 30일간의 대여 금액이 50만원 이상 200만원 미만인
order by fee desc, car_type, car_id desc -- [정렬]


-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능
-- 대여 기간에 2022-11이 포함된 자동차 id 골라내기
-- # select car_id
-- # from CAR_RENTAL_COMPANY_RENTAL_HISTORY h
-- 11월 전에 시작해서 11월 이상까지
-- # where (date_format(start_date,'%Y-%m')<'2022-11') and (date_format(end_date,'%Y-%m')>='2022-11')
-- 11월에 시작
-- # or (date_format(start_date,'%Y-%m')='2022-11')