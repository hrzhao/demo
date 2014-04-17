DROP PROCEDURE IF EXISTS `proc_userMsgList_get`;
DELIMITER //
CREATE DEFINER=`root`@`%` PROCEDURE `proc_userMsgList_get`()
BEGIN
SELECT
	c.`name`,
	COUNT(*) AS amount,
	c.realname,
	c.phone,
	c.address,
	c.room,
	c.building,
	c.type,
	c.intime,
	c.sex,
	max(m.createTime) as lastTime
FROM
	customer AS c
	INNER JOIN message AS m ON c.`name` = m.fromUserName
GROUP BY
	c.`name` ;
End
//
DELIMITER ;