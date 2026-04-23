function reindexFields(fieldsContainer) {
    const items = fieldsContainer.querySelectorAll('.field-item');
    items.forEach((item, index) => {
        // Update the visual index
        const indexSpan = item.querySelector('.field-index');
        if (indexSpan) indexSpan.textContent = index + 1;
        
        // Update input names and ids
        const inputs = item.querySelectorAll('input');
        inputs.forEach(input => {
            if (input.name) {
                input.name = input.name.replace(/fields\[\d+\]/, `fields[${index}]`);
            }
            if (input.id) {
                input.id = input.id.replace(/fields\d+\./, `fields${index}.`);
            }
        });
        
        // Update labels
        const labels = item.querySelectorAll('label');
        labels.forEach(label => {
            if (label.htmlFor) {
                label.htmlFor = label.htmlFor.replace(/fields\d+\./, `fields${index}.`);
            }
        });
    });
}

function initForm(document) {
    const addBtn = document.querySelector('button[name="addTextField"]');
    const fieldsContainer = document.querySelector('.fields-container');

    if (fieldsContainer) {
        // Event delegation for delete buttons
        fieldsContainer.addEventListener('click', function(e) {
            const deleteBtn = e.target.closest('.btn-delete');
            if (deleteBtn) {
                const fieldItem = deleteBtn.closest('.field-item');
                if (fieldItem) {
                    // Apply fade out animation before removing
                    fieldItem.style.animation = 'fadeIn 0.3s cubic-bezier(0.16, 1, 0.3, 1) reverse forwards';
                    setTimeout(() => {
                        fieldItem.remove();
                        reindexFields(fieldsContainer);
                    }, 300);
                }
            }
        });
    }

    if (addBtn && fieldsContainer) {
        addBtn.addEventListener('click', function(e) {
            // Prevent the button from submitting the form and refreshing the page
            e.preventDefault();
            
            // Count how many fields exist to determine the next index for Spring Boot binding
            const index = fieldsContainer.querySelectorAll('.field-item').length;
            
            // Create the HTML for the new field
            const templateEl = document.getElementById('field-template-TEXT');
            if (templateEl) {
                const templateHtml = templateEl.innerHTML;
                const newFieldHtml = templateHtml
                    .replace(/_INDEX_ADD_1/g, index + 1)
                    .replace(/_INDEX_/g, index);
                
                // Append the new field to the container
                fieldsContainer.insertAdjacentHTML('beforeend', newFieldHtml);
            }
        });
    }
}

// Export for CommonJS (Jest testing) or attach to DOMContentLoaded for browser
if (typeof module !== 'undefined' && module.exports) {
    module.exports = { initForm, reindexFields };
} else {
    document.addEventListener('DOMContentLoaded', function() {
        initForm(document);
    });
}
