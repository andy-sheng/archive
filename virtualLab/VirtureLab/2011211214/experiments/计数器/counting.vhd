library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity counting is
port(
	clk: in std_logic;
	cout: out std_logic_vector(7 downto 0));
end counting;

architecture behav of counting is

signal nextcount: integer range 0 to 7;

begin

process(clk)
begin
	if(clk'event and clk='1')then
		nextcount <= nextcount + 1;
		case nextcount is
			when 0 => cout <= "11111111";
			when 1 => cout <= "11111110";
			when 2 => cout <= "11111101";
			when 3 => cout <= "11111100";
			when 4 => cout <= "11111011";
			when 5 => cout <= "11111010";
			when 6 => cout <= "11111001";
			when others => cout <= "11111000";
		end case;
	end if;
end process;
end behav;