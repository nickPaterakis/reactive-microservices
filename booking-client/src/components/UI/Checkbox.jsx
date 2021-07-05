import React from 'react';
import PropTypes from 'prop-types';

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

Checkbox.propTypes = {
  onChange: PropTypes.func.isRequired,
  name: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  isChecked: PropTypes.bool.isRequired,
};

export default Checkbox;
