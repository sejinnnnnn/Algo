-- 코드를 작성해주세요
select i.ID as `ID`, n.FISH_NAME as `FISH_NAME`, i.LENGTH as `LENGTH`
from FISH_INFO i, FISH_NAME_INFO n
where i.FISH_TYPE = n.FISH_TYPE
    and i.LENGTH = (select max(j.LENGTH) from FISH_INFO j where j.FISH_TYPE = i.FISH_TYPE);