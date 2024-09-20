
/*
SELECT
rp.familyName AS Relatives_FamilyName,
rp.givenName AS Relatives_GivenName,
rp.middleName AS Relatives_MiddleName,
rp.birthDate AS Relatives_BirthDate,
pd.contactRelationship AS Relationship
FROM
HPPersonGeneric AS emp
JOIN
HPPersonDependant AS pd ON emp.sysId = pd.HPPersonGenericSysId
JOIN
HPPersonGeneric AS rp ON pd.HPRelatedPersonSysId = rp.sysId
WHERE
emp.personId = 'test'
*/