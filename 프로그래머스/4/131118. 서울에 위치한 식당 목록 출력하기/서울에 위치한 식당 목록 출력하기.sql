-- 코드를 입력하세요
select i.REST_ID as `REST_ID`, i.REST_NAME as `REST_NAME`, i.FOOD_TYPE as `FOOD_TYPE`, 
    i.FAVORITES as `FAVORITES`, i.ADDRESS as `ADDRESS`, round(avg(r.REVIEW_SCORE), 2) as `SCORE`
from REST_INFO i, REST_REVIEW r
where i.REST_ID = r.REST_ID and substr(i.ADDRESS, 1, 2) = '서울'
group by i.REST_ID
order by SCORE desc, i.FAVORITES desc;