-- 코드를 작성해주세요
select year(e.DIFFERENTIATION_DATE) as `YEAR`, 
    (select max(e.SIZE_OF_COLONY) from ECOLI_DATA e where YEAR = year(e.DIFFERENTIATION_DATE))
    - e.SIZE_OF_COLONY as `YEAR_DEV`,
    e.ID as `ID`
from ECOLI_DATA e
order by `YEAR` asc, `YEAR_DEV` asc;