LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY led IS
PORT(clk, choose,reset:IN STD_LOGIC;
	 cout:OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
END led;
