-- 코드를 입력하세요
select p.PRODUCT_ID, p.PRODUCT_NAME, sum(p.PRICE * o.AMOUNT) as `TOTAL_SALES`
from FOOD_PRODUCT p join FOOD_ORDER o on p.PRODUCT_ID = o.PRODUCT_ID
where year(o.PRODUCE_DATE) = 2022 and month(o.PRODUCE_DATE) = 5
group by p.PRODUCT_ID
order by `TOTAL_SALES` desc, p.PRODUCT_ID asc;