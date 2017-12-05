for name in ls *.c
do
	x86_64-w64-mingw32-gcc $name -o out/$name.exe
	gcc $name -o out/$name.elf
done
