library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity sep is
port(
	clk_in: in std_logic;
	clk_out: out std_logic
);
end sep;

architecture beh of sep is
signal count: integer range 0 to 1;
signal q: std_logic;

begin
process(clk_in, q)
begin
	if clk_in'event and clk_in='1' then
		if count=1 then
			count <= 0;
			q <= not q;
		else count <= count+1;
		end if;
	end if;
	clk_out <= q;
end process;
end beh;