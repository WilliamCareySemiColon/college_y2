for NAME in ls *.c
do
	gcc $NAME -o out/$NAME.elf
done
