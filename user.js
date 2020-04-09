db.createUser({
	user : "myUserAdmin",
	pwd : "abc123",
	roles : [ {
		role : "readWrite",
		db : "employee-management"
	} ]
});

db.sequence.insert({
	_id : "employeesequencenumber",
	seq : 0
});
