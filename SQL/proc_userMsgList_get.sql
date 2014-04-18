DROP PROCEDURE IF EXISTS `proc_userMsgList_get`;
DELIMITER //
CREATE DEFINER=`root`@`%` PROCEDURE `proc_userMsgList_get`()
BEGIN
SELECT
	c.`name`,
	COUNT(*) AS amount,
	c.realname,
	c.intime,
	m.toUserName,
	max(m.createTime) as lastTime
FROM
	customer AS c
	INNER JOIN message AS m ON c.`name` = m.fromUserName
GROUP BY
	c.`name`,m.toUserName ;
End
//
DELIMITER ;