import os
import time
import pathlib

# Database details
DB_HOST = 'localhost'
DB_PORT = '8003'
DB_USER = 'postgres'
DB_PASS = 'postgres'
DB_NAME = 'postgres'

# Backup details
BACKUP_PATH = './'
TIMESTAMP = time.strftime('%d-%m-%Y-%H-%M')
BACKUP_FILE = DB_NAME + '-' + TIMESTAMP + '.tar'

# Command to take a backup

BACKUP_CMD = ".\pg_dump\pg_dump.exe -h {0} -p {1} -U {2} -d {3} > {4}".format(DB_HOST, DB_PORT, DB_USER, DB_NAME, os.path.join(BACKUP_PATH, BACKUP_FILE))

# Execute the backup command
os.system(BACKUP_CMD)