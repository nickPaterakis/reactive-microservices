import React from 'react';

function Checkbox({
  onChange, name, value, isChecked, 
}) {
  return (
    <li>
      <label htmlFor={name} className="checkbox-item">
        {name}
        <input 
          type="checkbox" 
          id={name}
          value={value}
          onChange={(e) => onChange(e)}
          checked={isChecked}
        />
        <span className="checkbox-item__checkmark" />
      </label>
    </li>
  );
}

export default Checkbox;
