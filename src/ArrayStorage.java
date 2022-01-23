import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage
{
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear()
    {
        for (int i = 0; i < size - 1; i++)
        {
            storage[i] = null;
        }

        size = 0;
    }

    void save(Resume resume)
    {
        if (resume == null) return;

        if (resume.uuid.isEmpty()) return;

        for (int i = 0; i < storage.length; i++)
        {
            if (storage[i].uuid.equals(resume.uuid))
            {
                // Перезаписывать или выбрасить Exception???
                storage[i] = resume;
                return;
            }
        }

        if ((size + 1) > storage.length)
        {
            // Что делать при переполнении базы???
            return;
        }

        storage[size++] = resume;
    }

    Resume get(String uuid)
    {
        return null;
    }

    void delete(String uuid)
    {
        if(uuid.isEmpty())
        {
            return;
        }

        int indexOfNull = -1;

        for (int i = 0; i < storage.length; i++)
        {
            if(storage[i].uuid.equals(uuid))
            {
                storage[i] = null;
                indexOfNull = i;
                break;
            }
        }

        if (indexOfNull == -1)
        {
            return;
        }

        if(indexOfNull != (size - 1))
        {
            storage[indexOfNull] = storage[size -1];
            storage[size -1] = null;
        }

        size--;

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll()
    {
        return Arrays.copyOf(storage, size);
    }

    int size()
    {
        return size;
    }
}
