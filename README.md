#  Task manager
### Version is *1.0-SNAPSHOT*

Console task manager is a test task for [ProgForce](http://progforce.com.ua). Store tasks in *MySQL* database

### Features:

* simple CLI interface
* user can add task
* user can see active tasks. If task is expired, it marks as *EXPIRED*
* user can see closed tasks
* user can close active task

### CLI:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Please choose an operation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1 - Add new task
2 - Show tasks

0 - Exit
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

2
#####	All active tasks	#####
id: 11		Description:	Create test task for ProgForce
			Time:			30.10.2016 15:00
			Priority:		HIGH
			

id: 12		Description:	Do something
			Time:			20.10.2016 11:00
			Priority:		LOW
			EXPIRED

```
