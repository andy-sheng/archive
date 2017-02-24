library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity led is
port(
	clk: in std_logic;--时间信号
	cout: out std_logic_vector(7 downto 0));--八盏LED灯输出
end led;

architecture behav of led is
begin



end behav;